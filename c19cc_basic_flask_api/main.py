# importing from website package, __init__.py turns folders into python package
from website import create_app

# assigning flask app function to a variable
app = create_app()

# will only execute app if you run the file not import it
if __name__ == '__main__':
    # any change to the python code will rerun the webserver, comment out before production
    app.run()

#(debug=True)
