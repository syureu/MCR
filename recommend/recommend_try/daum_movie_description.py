import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer, CountVectorizer
from sklearn.metrics.pairwise import linear_kernel, cosine_similarity
from konlpy.tag import Kkma
from konlpy.utils import pprint
import sys

count = 10
pd.set_option("display.max_colwidth", 500)

md = pd.read_csv('../resources/daum_movie_description_qualified_big.csv')
md['nouns_string'] = md['nouns_string'].fillna('')
tf = TfidfVectorizer(min_df=1)
tfidf_matrix = tf.fit_transform(md['nouns_string'])
cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)
md = md.reset_index()
index_series = md['movie_id']

indices = pd.Series(md.index, index=md['movie_id'])


def get_recommendations(index):
    idx = indices[index]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:31]
    movie_indices = [i[0] for i in sim_scores]
    return md.iloc[movie_indices]


ret = get_recommendations(int(sys.argv[1])).head(
    10)

l1 = ret['movie_id'].head(
    count).to_string(index=False, header=False).split("\n")
l2 = ret['movie_name'].head(
    count).to_string(index=False, header=False).split("\n")
l3 = ret['img_url'].head(
    count).fillna('null').to_string(index=False, header=False).split("\n")

for i in l1:
    print(i.strip())
for i in l2:
    print(i.strip())
for i in l3:
    print(i.strip())
