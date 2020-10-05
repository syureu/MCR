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
from surprise import Reader, Dataset, SVD
from surprise.model_selection import cross_validate
from konlpy.tag import Kkma
from konlpy.utils import pprint

import datetime as dt

print("1. 실행시작")
print(dt.datetime.now())
print()

kkma = Kkma()

md = pd.read_csv('../resources/daum_movie_202010042105.csv')

print("2. 로딩 끝 - 빈 줄거리 제거 시작")
print(dt.datetime.now())
print()

print(md)
print()

md['overview'] = md['overview'].fillna('')

print("3. 빈 줄거리 제거 완료 - 한글 명사 분석 시작")
print(dt.datetime.now())
print()

print(md)
print()


print(md['overview'])
print(md['overview'][0])
print(kkma.nouns(md['overview'][0]))
print(" ".join(kkma.nouns(md['overview'][0])))


md['nouns_string'] = md.apply(
    lambda x: " ".join(kkma.nouns(x['overview'])), axis=1)

md.to_csv('../resources/daum_movie_description_qualified_big.csv')

print("4. 한글 명사 분석 완료 - tfidf 행렬 생성 시작")
print(dt.datetime.now())
print()

"""

print(md)
print()

print("파일 로딩중")
print(dt.datetime.now())

md = pd.read_csv('../resources/daum_movie_description_qualified.csv')

print("파일 로딩 끝, fillna 수행")
print(dt.datetime.now())


md['nouns_string'] = md['nouns_string'].fillna('')


print("fillna 수행 완료, vectorizer 수행")
print(dt.datetime.now())

# tf = TfidfVectorizer(analyzer='word', ngram_range=(1, 2),
#                     min_df=0, stop_words='english')
tf = TfidfVectorizer(min_df=1)
tfidf_matrix = tf.fit_transform(md['nouns_string'])
#doc_dist = (tfidf_matrix * tfidf_matrix.T)

print("vectorizer 수행 끝, linear 수행")
print(dt.datetime.now())

cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)

print("linear수행 끝, 제목컷")
print(dt.datetime.now())

md = md.reset_index()
#titles = md['movie_name']
index_series = md['movie_id']
#indices = pd.Series(md.index, index=md['movie_name'])
indices = pd.Series(md.index, index=md['movie_id'])

print("제목컷 완료")
print(dt.datetime.now())


# def get_recommendations(title):
def get_recommendations(index):
    #idx = indices[title]
    idx = indices[index]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:31]
    movie_indices = [i[0] for i in sim_scores]
    return index_series.iloc[movie_indices]


print(get_recommendations(3).head(10))
print()

print("함수 수행 완료")
print(dt.datetime.now())

"""
