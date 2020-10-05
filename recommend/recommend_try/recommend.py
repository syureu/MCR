import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from scipy import stats
from ast import literal_eval
from sklearn.feature_extraction.text import TfidfVectorizer, CountVectorizer
from sklearn.metrics.pairwise import linear_kernel, cosine_similarity
from nltk.stem.snowball import SnowballStemmer
from nltk.stem.wordnet import WordNetLemmatizer
from nltk.corpus import wordnet

#from surprise import Reader, Dataset, SVD, evaluate

# pip install scikit-surpirse
from surprise import Reader, Dataset, SVD

from surprise.model_selection import cross_validate

#import warnings; warnings.simplefilter('ignore')

"""

md = pd.read_json('../resources/tmpdata.json')
print(md.head())

# pandas fillna NaN을 다른 값으로 대체
# ast literal_eval evaluation 해주는거라고 생각하면 됨
md['genres'] = md['genres'].fillna('[]').apply(lambda x: [i['name']
                                                          for i in x] if isinstance(x, list) else [])
# .apply(literal_eval)
# .apply(lambda x: [i['name'] for i in x] if isinstance(x, list) else [])

# print(tmp)

vote_counts = md[md['vote_count'].notnull()]['vote_count'].astype('int')
vote_averages = md[md['vote_average'].notnull()]['vote_average'].astype('int')
C = vote_averages.mean()
print(C)
# test 영화 데이터가 너무 적어서 95% 컷을 하면 영화가 얼마 되지 않으므로
# 조정하여 테스트
#m = vote_counts.quantile(0.95)
m = vote_counts.quantile(0.5)
print(m)
md['year'] = pd.to_datetime(md['release_date'], errors='coerce').apply(
    lambda x: str(x).split('-')[0] if x != np.nan else np.nan)
qualified = md[(md['vote_count'] >= m) & (md['vote_count'].notnull()) & (md['vote_average'].notnull())][[
    'title', 'year', 'vote_count', 'vote_average', 'popularity', 'genres']]
qualified['vote_count'] = qualified['vote_count'].astype('int')
qualified['vote_average'] = qualified['vote_average'].astype('int')
print(qualified.shape)


def weighted_rating(x):
    v = x['vote_count']
    R = x['vote_average']
    return (v/(v+m) * R) + (m/(m+v) * C)


qualified['wr'] = qualified.apply(weighted_rating, axis=1)
qualified = qualified.sort_values('wr', ascending=False).head(250)
print(qualified.head(15))

# construct our function that builds charts for particular genres
#print("before build_chart")
s = md.apply(lambda x: pd.Series(x['genres']),
             axis=1).stack().reset_index(level=1, drop=True)
s.name = 'genre'
# print(s)
gen_md = md.drop('genres', axis=1).join(s)
# print(gen_md)


# def build_chart(genre, percentile=0.85):
def build_chart(genre, percentile=0.5):
    #print("build_chart : 1")
    df = gen_md[gen_md['genre'] == genre]
    vote_counts = df[df['vote_count'].notnull()]['vote_count'].astype('int')
    vote_averages = df[df['vote_average'].notnull()
                       ]['vote_average'].astype('int')
    C = vote_averages.mean()
    m = vote_counts.quantile(percentile)

    # print(df)
    # print(vote_counts)
    # print(vote_averages)
    # print(C)
    # print(m)

    #print("build_chart : 2")

    # df에서 m값이상 투표를 받은것만 건져내서 컬럼 조정
    qualified = df[(df['vote_count'] >= m) & (df['vote_count'].notnull()) & (
        df['vote_average'].notnull())][['title', 'year', 'vote_count', 'vote_average', 'popularity']]
    # print(qualified)
    qualified['vote_count'] = qualified['vote_count'].astype('int')
    # print(qualified)
    qualified['vote_average'] = qualified['vote_average'].astype('int')
    # 위의 두 컬럼 정수형으로 변경(원래 부동소수)
    # print(qualified)

    print("build_chart : 3")
    # print(qualified['vote_count'])
    # print(qualified['vote_count']+m)
    # print(qualified['vote_average'])
    # print(m+qualified['vote_count'])

    qualified['wr'] = qualified.apply(lambda x: (
        x['vote_count']/(x['vote_count']+m) * x['vote_average']) + (m/(m+x['vote_count']) * C), axis=1)
    qualified = qualified.sort_values('wr', ascending=False).head(250)

    return qualified


print(build_chart('Science Fiction').head(15))

"""

# print()
# print("CBF")
# print()

mydata = pd.read_csv('../resources/tmdb_movies_202009151556.csv')
mydataid = mydata[mydata['id'].notnull()]['id'].astype('int')

mymd = mydata
mymd['id'] = mydata['id'].astype('int')
smd = mymd[mymd['id'].isin(mydataid)]

# print(smd.shape)
# print(smd)
# print()

smd['tagline'] = smd['tagline'].fillna('')
smd['description'] = smd['overview'] + smd['tagline']
smd['description'] = smd['description'].fillna('')

# print(smd)
# print()

tf = TfidfVectorizer(analyzer='word', ngram_range=(1, 2),
                     min_df=0, stop_words='english')
tfidf_matrix = tf.fit_transform(smd['description'])

# print(tfidf_matrix.shape)
print(tfidf_matrix)
print()

cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)
print(cosine_sim[0])

smd = smd.reset_index()
titles = smd['title']
indices = pd.Series(smd.index, index=smd['title'])
print(titles)
print(indices)


def get_recommendations(title):
    idx = indices[title]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:31]
    movie_indices = [i[0] for i in sim_scores]
    return titles.iloc[movie_indices]


print(get_recommendations('The Godfather').head(10))
print()
print(get_recommendations('Batman Returns').head(10))
print()
