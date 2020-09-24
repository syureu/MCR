import pandas as pd
import numpy as np
import sys

gen_md = pd.read_csv('../resources/open_api_qualified.csv', dtype={
                     'movie_cd': np.str, 'movie_nm': np.str, 'tmdb_poster_path': np.str, 'vote_count': np.int, 'vote_average': np.double})
genre = sys.argv[1]
count = 15
if len(sys.argv) >= 3:
    count = int(sys.argv[2])
percentile = 0.85

df = gen_md[gen_md['genre'] == genre]

vote_counts = df[df['vote_count'].notnull()]['vote_count'].astype('int')
vote_averages = df[df['vote_average'].notnull()
                   ]['vote_average'].astype('int')
C = vote_averages.mean()
m = vote_counts.quantile(percentile)

qualified = df[(df['vote_count'] >= m) & (df['vote_count'].notnull()) & (
    df['vote_average'].notnull())][['movie_cd', 'movie_nm', 'tmdb_poster_path', 'vote_count', 'vote_average']]
qualified['vote_count'] = qualified['vote_count'].astype('int')
qualified['vote_average'] = qualified['vote_average'].astype('int')

qualified['wr'] = qualified.apply(lambda x: (
    x['vote_count']/(x['vote_count']+m) * x['vote_average']) + (m/(m+x['vote_count']) * C), axis=1)

qualified = qualified.sort_values('wr', ascending=False).head(250)

# print(qualified[['movie_cd', 'movie_nm', 'tmdb_poster_path']].head(
#    count).to_string(index=False, header=False))

l1 = qualified['movie_cd'].head(
    count).to_string(index=False, header=False).split("\n")
l2 = qualified['movie_nm'].head(
    count).to_string(index=False, header=False).split("\n")
l3 = qualified['tmdb_poster_path'].head(
    count).to_string(index=False, header=False).split("\n")

for i in l1:
    print(i.strip())
for i in l2:
    print(i.strip())
for i in l3:
    print(i.strip())
