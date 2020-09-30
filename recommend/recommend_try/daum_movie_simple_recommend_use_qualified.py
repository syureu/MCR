import pandas as pd

count = 10

qualified = pd.read_csv('../resources/daum_movie_no_genre_based_qualified.csv')

pd.set_option("display.max_colwidth", 500)

l1 = qualified['movie_id'].head(
    count).to_string(index=False, header=False).split("\n")
l2 = qualified['movie_name'].head(
    count).to_string(index=False, header=False).split("\n")
l3 = qualified['img_url'].head(
    count).to_string(index=False, header=False).split("\n")

for i in l1:
    print(i.strip())
for i in l2:
    print(i.strip())
for i in l3:
    print(i.strip())
