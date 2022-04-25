from selenium import webdriver
from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
import pandas as pd
import mysql.connector
from sqlalchemy import create_engine

#MySQL database connection
db = mysql.connector.connect(
	host="localhost",
	user="root",
	passwd="root",
	database="covid19criticalcare")

#Cursor for navigating MySQL database
mycursor = db.cursor()

url = 'https://covid19criticalcare.com/pharmacies/'

driver = webdriver.Chrome(ChromeDriverManager().install())
driver.maximize_window()
driver.get(url)
wait = WebDriverWait(driver, 5)
        
select = Select(wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, '[name = "DataTables_Table_0_length"'))))
select.select_by_value('-1')
wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, 'a.paginate_button.next.disabled')))

df = pd.read_html(driver.page_source, displayed_only=False)[1]
driver.close()

#updates table with new data
mycursor.execute("DROP TABLE IF EXISTS pharmarcy_info")

#creates connection to MySQL database
engine = create_engine('mysql+mysqlconnector://{user}:{password}@{server}/{database}'.format(user='root', password='root', server='localhost', database='covid19criticalcare'))

#exports excel into MySQL
df.to_sql('pharmacy_info', con=engine)

