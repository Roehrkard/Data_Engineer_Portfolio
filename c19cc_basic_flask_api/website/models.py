# database models for Users and Symptoms

# from website (i.e. this package) import available packages
from . import db
from flask_login import UserMixin
from sqlalchemy.sql import func

class Symptoms(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    data = db.Column(db.String(10000))
    # func.now will automatically input the current date
    date = db.Column(db.DateTime(timezone=True), default=func.now())
    # connects Symptoms & class through ForeignKey user.id
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'))


# class that inherits from db.model and UserMixin (UserMixin with current_user allows you to access all the information of the currently logged in user
class User(db.Model, UserMixin):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(150), unique=True)
    password = db.Column(db.String(150))
    first_name = db.Column(db.String(150))
    last_name = db.Column(db.String(150))
    address = db.Column(db.String(150))
    city = db.Column(db.String(150))
    state = db.Column(db.String(150))
    zipcode = db.Column(db.String(150))
    # Connects the User table to the Symptoms table, relationship has to be capital, foreign key lowercase
    symptom = db.relationship('Symptoms')