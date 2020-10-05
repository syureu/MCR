import pandas as pd

pd.set_option("display.max_colwidth", 500)
md = pd.read_csv('../resources/daum_movie_description_qualified_big.csv')
vote_counts = md[md['count'].notnull()]['count'].astype('int')
vote_averages = md[md['rate'].notnull()]['rate'].astype('int')
C = vote_averages.mean()
m = vote_counts.quantile(0.85)

qualified = md[(md['count'] >= m) & (
    md['count'].notnull()) & (md['rate'].notnull())][[
        'movie_name', 'movie_name_en', 'movie_id', 'rate', 'genre', 'movie_opening_date', 'running_time', 'overview', 'img_url', 'nation', 'score', 'adult', 'count', 'nouns_string']]


def weighted_rating(x):
    v = x['count']
    R = x['rate']
    return (v/(v+m) * R) + (m/(m+v) * C)


qualified['wr'] = qualified.apply(weighted_rating, axis=1)
qualified = qualified.sort_values('wr', ascending=False)
qualified.to_csv('../resources/daum_movie_description_qualified_big.csv')
