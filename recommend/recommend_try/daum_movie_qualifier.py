import pandas as pd
import sys
import datetime as dt

print(1)
print(dt.datetime.now())

md = pd.read_csv('../resources/daum_movie_202009281326.csv')

print(md)
print()

print(2)
print(dt.datetime.now())

md['genre'] = md['genre'].fillna('[]').apply(lambda x: x.split('/'))
print(md)
print()

print(3)
print(dt.datetime.now())

s = md.apply(lambda x: pd.Series(x['genre']),
             axis=1).stack().reset_index(level=1, drop=True)
s.name = 'genre'
gen_md = md.drop('genre', axis=1).join(s)
print(s)
print()
print(gen_md)
print()

gen_md.to_csv('../resources/daum_movie_qualified.csv')

print(4)
print(dt.datetime.now())

genre = sys.argv[1]
percentile = 0.95

df = gen_md[gen_md['genre'] == genre]
vote_counts = df[df['rate'].notnull()]['rate'].astype('int')
vote_averages = df[df['rate'].notnull()
                   ]['rate'].astype('int')
C = vote_averages.mean()
m = vote_counts.quantile(percentile)
print(df)
print(vote_counts)
print(vote_averages)
print(C)
print(m)
print()

print(5)
print(dt.datetime.now())

qualified = df[(df['rate'] >= m) & (df['rate'].notnull()) & (
    df['rate'].notnull())][['movie_id', 'movie_name', 'img_url', 'count', 'rate']]
qualified['count'] = qualified['count'].astype('int')
qualified['rate'] = qualified['rate'].astype('int')
print(qualified)
print()

print(6)
print(dt.datetime.now())

qualified['wr'] = qualified.apply(lambda x: (
    x['count']/(x['count']+m) * x['rate']) + (m/(m+x['count']) * C), axis=1)
qualified = qualified.sort_values('wr', ascending=False).head(10)

print(qualified)
print()

print(7)
print(dt.datetime.now())
