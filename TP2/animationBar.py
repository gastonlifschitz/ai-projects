import plotly.express as px
import pandas as pd
import scanFile as scanner

matrix = scanner.scanarr('animationBar.txt')

#print(matrix)

dfaux = pd.DataFrame(matrix,columns=['label','generacion','value'])

print(dfaux)

#df = px.data.gapminder()

#print(df)

fig = px.bar(dfaux, x="label", y="value",
  animation_frame="generacion", animation_group="label", range_y=[0,50])
fig.show()