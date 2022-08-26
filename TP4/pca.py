from sklearn.decomposition import PCA
from sklearn.preprocessing import StandardScaler, MinMaxScaler
import pandas as pd

europe = pd.read_csv('europe.csv')
s = StandardScaler()
numsH = [col for col in europe.columns if col != 'Country']
data = pd.DataFrame(s.fit_transform(europe[numsH]), index=europe.index, columns=numsH)
fullNormalizedData = pd.DataFrame(europe['Country']).join(data)
headers = list(europe.columns)
print(headers)
DATA = data.values

ppalcomp = PCA()
ppalcomp.fit(DATA)
print(ppalcomp.components_[0])
h = ['Area', 'GDP', 'Inflation', 'Life.expect', 'Military', 'Pop.growth', 'Unemployment']
print(zip(h,ppalcomp.components_[0]))
for z in zip(h,ppalcomp.components_[0]):
	print(z)