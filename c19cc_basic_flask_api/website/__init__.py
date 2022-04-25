# Creates a flask API app function and turns website into a pythong package
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from os import path
from flask_login import LoginManager


# database
db = SQLAlchemy()
# database object
DB_NAME = "database.db"

# Flask application
def create_app():
    app = Flask(__name__)
    # encrypts/secures cookies and session data from website
    app.config['SECRET_KEY'] = 'qwert'
    # location of you database, f string evaluates python code as a string
    app.config['SQLALCHEMY_DATABASE_URI'] = f'sqlite:///{DB_NAME}'
    # communicates that you are going to use the flask api with the DB
    db.init_app(app)


    # imports views and auth functions to the flask application
    from .views import views
    from .auth import auth

    # '/' assigns no prefix to the @ of the blueprint otherwise you'd have to add prefix before @ string
    app.register_blueprint(views, url_prefix='/')
    app.register_blueprint(auth, url_prefix='/')

    # . relative import, testing that .models can be imported before initializing/creating the database
    from .models import User, Symptoms

    create_database(app)

    login_manager = LoginManager()
    login_manager.login_view = 'auth.login'
    login_manager.init_app(app)

    @login_manager.user_loader
    def load_user(id):
        # get will automatically search for primary key
        return User.query.get(int(id))

    return app

# function makes sure the databases doesn't already exist
def create_database(app):
    if not path.exists('website/' + DB_NAME):
        db.create_all(app=app)
        print('Created Database!')