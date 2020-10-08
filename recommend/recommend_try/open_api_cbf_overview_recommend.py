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

md = pd.read_csv('../resources/movie_202009231607.csv')
md['description'] = md['tmdb_overview'].fillna('')
print(md)
print()

tf = TfidfVectorizer(analyzer='word', ngram_range=(1, 2),
                     min_df=0, stop_words='english')
tfidf_matrix = tf.fit_transform(md['description'])

print(tfidf_matrix)
print()

cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)
print(cosine_sim)
print()

md = md.reset_index()
titles = md['movie_nm']
indices = pd.Series(md.index, index=md['movie_nm'])


def get_recommendations(title):
    idx = indices[title]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:31]
    movie_indices = [i[0] for i in sim_scores]
    return titles.iloc[movie_indices]


print(get_recommendations('파스퇴르').head(10))
print()
print(get_recommendations('작은 아씨들').head(10))
print()
