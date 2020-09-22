import pandas as pd
import json
import datetime

print()
print(datetime.datetime.now())
print()

md = pd.read_csv('../resources/tmdb_movies_202009171739.csv')

print()
print(datetime.datetime.now())
print()

md['genres'] = md['genres'].apply(lambda x: json.loads(x))

print()
print(datetime.datetime.now())
print()

md['genres'] = md['genres'].fillna('[]').apply(lambda x: [i['name']
                                                          for i in x] if isinstance(x, list) else [])

print()
print(datetime.datetime.now())
print()

s = md.apply(lambda x: pd.Series(x['genres']),
             axis=1).stack().reset_index(level=1, drop=True)
s.name = 'genre'

print()
print(datetime.datetime.now())
print()

gen_md = md.drop('genres', axis=1).join(s)

print()
print(datetime.datetime.now())
print()

print(gen_md)
print()
#df = gen_md[gen_md['genre'] == genre]
df = gen_md
vote_counts = df[df['vote_count'].notnull()]['vote_count'].astype('int')
vote_averages = df[df['vote_average'].notnull()
                   ]['vote_average'].astype('int')
C = vote_averages.mean()
m = vote_counts.quantile(0.85)

qualified = df[(df['vote_count'] >= m) & (df['vote_count'].notnull()) & (
    df['vote_average'].notnull())][['title', 'release_date', 'vote_count', 'vote_average', 'popularity']]

qualified['vote_count'] = qualified['vote_count'].astype('int')
qualified['vote_average'] = qualified['vote_average'].astype('int')
qualified['wr'] = qualified.apply(lambda x: (
    x['vote_count']/(x['vote_count']+m) * x['vote_average']) + (m/(m+x['vote_count']) * C), axis=1)
qualified = qualified.sort_values('wr', ascending=False)
qualified.to_csv('../resources/tmdb_qualified.csv', sep=',', na_rep='NaN')
