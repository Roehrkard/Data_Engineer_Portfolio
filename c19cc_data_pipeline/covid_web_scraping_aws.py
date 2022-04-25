from bs4 import BeautifulSoup
import requests
import mysql.connector
import pandas as pd
from sqlalchemy import create_engine

def lambda_handler(event, context):

	#MySQL database connection
	db = mysql.connector.connect(
		host="rds_url",
		user="root",
		passwd="root",
		database="covid19criticalcare")

	#Cursor for navigating MySQL database
	mycursor = db.cursor()

	#Scraped url
	url = "https://covid19criticalcare.com/ivermectin-in-covid-19/covid-19-care-providers/"

	#Need headers to get past 403 forbidden error
	#result is the scraped html code
	#Beautiful soup allows parses through the data, allowing you to select from specified tags & classes
	headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36'}
	result = requests.get(url, headers = headers)
	doc = BeautifulSoup(result.text, "html.parser")

	#locates each row of data from the td tag, class/column
	name = doc.find_all("td", class_="column-1")
	location = doc.find_all("td", class_="column-2" )

	#lists to store scraped data
	td_names = []
	td_locations = []


	#for loops to convert data into text 
	for td in name:
		names = td.text.replace("\n", " ").replace("[email\xa0protected]", "").replace("\xa0AL", "")
		td_names.append(names)

	#commented out code for debugging
	#print(td_names)


	for td in location:
		locations = td.contents[0].replace("Z_", "")
		td_locations.append(locations)

	#commented out code for debugging
	#print(td_locations)


	#turning list of data into panda keys
	df = pd.DataFrame({'names':td_names,'locations':td_locations})

	#saving panda keys into an excel sheet
	#df.to_csv('covid19criticalcare.csv', index=False, encoding='utf-8')

	#updates table with new data
	mycursor.execute("DROP TABLE IF EXISTS names_locations")

	#assign excel file to variable
	#df = pd.read_csv('covid19criticalcare.csv')

	#creates connection to MySQL database
	engine = create_engine('mysql+mysqlconnector://{user}:{password}@{server}/{database}'.format(user='root', password='root', server='rds_url', database='covid19criticalcare'))

	#exports excel into MySQL
	df.to_sql('names_locations', con=engine)

	print("it worked!")





