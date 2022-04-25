from flask import Blueprint, render_template, request, flash, jsonify
from flask_login import login_required, current_user
from .models import Symptoms
from . import db
import json

# Stores roots for where users can navigate between
# Blueprints allow you to split up views and organize them beteen files
views = Blueprint('views', __name__)


# hitting the route will call the function below
# '/' routes visitor to homepage
@views.route('/', methods=['GET', 'POST'])
@login_required
def home():
    if request.method == 'POST':
        symptoms = request.form.get('symptoms')

        if len(symptoms) < 1:
            flash('Symptoms is too short!', category='error')
        else:
            new_symptoms = Symptoms(data=symptoms, user_id=current_user.id)
            db.session.add(new_symptoms)
            db.session.commit()
            flash('Symptoms added!', category='success')

    return render_template("home.html", user=current_user)


@views.route('/delete-symptoms', methods=['POST'])
def delete_symptoms():
    symptoms = json.loads(request.data)
    symptomsId = symptoms['symptomsId']
    symptoms = Symptoms.query.get(symptomsId)
    if symptoms:
        if symptoms.user_id == current_user.id:
            db.session.delete(symptoms)
            db.session.commit()

    return jsonify({})
