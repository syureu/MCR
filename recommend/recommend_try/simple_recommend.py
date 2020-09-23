from surprise.model_selection import cross_validate
from surprise import Reader, Dataset, SVD
from nltk.corpus import wordnet
from nltk.stem.wordnet import WordNetLemmatizer
from nltk.stem.snowball import SnowballStemmer
from sklearn.metrics.pairwise import linear_kernel, cosine_similarity
from sklearn.feature_extraction.text import TfidfVectorizer, CountVectorizer
from ast import literal_eval
from scipy import stats
import seaborn as sns
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import sys
import json
import datetime

# from surprise import Reader, Dataset, SVD, evaluate

# pip install scikit-surpirse

print(datetime.datetime.now())
md = pd.read_csv('../resources/tmdb_movies_202009171739.csv')
print(datetime.datetime.now())
md['genres'] = md['genres'].apply(lambda x: json.loads(x))
md['genres'] = md['genres'].fillna('[]').apply(lambda x: [i['name']
                                                          for i in x] if isinstance(x, list) else [])
s = md.apply(lambda x: pd.Series(x['genres']),
             axis=1).stack().reset_index(level=1, drop=True)
s.name = 'genre'
gen_md = md.drop('genres', axis=1).join(s)


def build_chart(genre, percentile=0.99):
    df = gen_md[gen_md['genre'] == genre]
    vote_counts = df[df['vote_count'].notnull()]['vote_count'].astype('int')
    vote_averages = df[df['vote_average'].notnull()
                       ]['vote_average'].astype('int')
    C = vote_averages.mean()
    m = vote_counts.quantile(percentile)

    qualified = df[(df['vote_count'] >= m) & (df['vote_count'].notnull()) & (
        df['vote_average'].notnull())][['title', 'release_date', 'vote_count', 'vote_average', 'popularity']]
    qualified['vote_count'] = qualified['vote_count'].astype('int')
    qualified['vote_average'] = qualified['vote_average'].astype('int')
    qualified['wr'] = qualified.apply(lambda x: (
        x['vote_count']/(x['vote_count']+m) * x['vote_average']) + (m/(m+x['vote_count']) * C), axis=1)
    qualified = qualified.sort_values('wr', ascending=False).head(10)
    return qualified


print(build_chart(sys.argv[1]))
print(datetime.datetime.now())
