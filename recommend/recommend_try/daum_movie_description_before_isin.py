import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer, CountVectorizer
from sklearn.metrics.pairwise import linear_kernel, cosine_similarity
from konlpy.tag import Kkma
from konlpy.utils import pprint
import sys

md = pd.read_csv('../resources/daum_movie_description_qualified.csv')
md = md.reset_index()
indices = pd.Series(md.index, index=md['movie_id'])
if indices.get(int(sys.argv[1])) is not None:
    print(sys.argv[1])
else:
    print(None)
