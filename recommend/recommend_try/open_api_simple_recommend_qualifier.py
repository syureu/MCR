import pandas as pd
import numpy as np
import datetime

print('1. csv 불러오기 전')
print(datetime.datetime.now())
print()

md = pd.read_csv('../resources/movie_202009231607.csv')

print('2. csv 로딩 완료, 장르 수정할 수 있도록, 평점, 투표수 이름조정')
print(datetime.datetime.now())
print()

md['genre'] = md['genre'].fillna('').apply(lambda x: x.split(","))
md['overview'] = md['tmdb_overview']
md['vote_average'] = md['tmdb_vote_average']
md['vote_count'] = md['tmdb_vote_count']

print('3. 장르 apply')
print(datetime.datetime.now())
print()

s = md.apply(lambda x: pd.Series(x['genre']),
             axis=1).stack().reset_index(level=1, drop=True)

print('4. s에 이름 장르로 붙이기')
print(datetime.datetime.now())
print()

s.name = 'genre'

print('5. genre 드롭하고 조인')
print(datetime.datetime.now())
print()

gen_md = md.drop('genre', axis=1).join(s)
# print(gen_md)
# print()
gen_md.to_csv('../resources/open_api_qualified.csv', sep=',', na_rep='NaN')

print('6. 전처리 끝')
print(datetime.datetime.now())
print()


def build_chart(genre, percentile=0.85):

    print('7. 함수내부, 입력받은 장르와 같은 장르 선정')
    print(datetime.datetime.now())
    print()

    df = gen_md[gen_md['genre'] == genre]

    print('8. 투표수, 평점 전처리를 통한 평균과 임계값선정')
    print(datetime.datetime.now())
    print()

    vote_counts = df[df['vote_count'].notnull()]['vote_count'].astype('int')
    vote_averages = df[df['vote_average'].notnull()
                       ]['vote_average'].astype('int')
    C = vote_averages.mean()
    m = vote_counts.quantile(percentile)

    print('9. qualified라는 임계값치 이상의 새 df 생성')
    print(datetime.datetime.now())
    print()

    qualified = df[(df['vote_count'] >= m) & (df['vote_count'].notnull()) & (
        df['vote_average'].notnull())][['movie_nm', 'year', 'vote_count', 'vote_average']]
    qualified['vote_count'] = qualified['vote_count'].astype('int')
    qualified['vote_average'] = qualified['vote_average'].astype('int')

    print('10. 내부 계산식 적용')
    print(datetime.datetime.now())
    print()

    qualified['wr'] = qualified.apply(lambda x: (
        x['vote_count']/(x['vote_count']+m) * x['vote_average']) + (m/(m+x['vote_count']) * C), axis=1)

    print('11. 소팅하기')
    print(datetime.datetime.now())
    print()
    qualified = qualified.sort_values('wr', ascending=False).head(250)

    print('12. 리턴')
    print(datetime.datetime.now())
    print()

    return qualified


print('6.5 함수 실행 전')
print(datetime.datetime.now())
print()

print(build_chart('공포(호러)').head(15))
print()

print('13. 마무리')
print(datetime.datetime.now())
print()
