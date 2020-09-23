import pandas as pd

md = pd.read_csv('../resources/movie_202009211541.csv')

md['genre'] = md['genre'].fillna('').apply(lambda x: x.split(","))
print(md)
print()

s = md.apply(lambda x: pd.Series(x['genre']),
             axis=1).stack().reset_index(level=1, drop=True)
s.name = 'genre'
print(s)
print()

gen_md = md.drop('genre', axis=1).join(s)
print(gen_md)
print()


df = gen_md[gen_md['genre'] == '코미디']
print(df)
print()
