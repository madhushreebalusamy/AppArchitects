from flask import Flask, request, Response
from database.db import Database


app = Flask(__name__)
app.secret_key = "This is the secret key :: SecretKey"


@app.route('/', methods=['GET', 'POST'])
def index():
    return {
        "username": None
    }


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        db = Database()
        farmerID = request.form.get('farmerID', None)
        deviceDetails = request.form.get('deviceDetails', None)
        resp = db.getSessionID(farmerID, deviceDetails)
        if resp.rowcount > 0:
            return {
                "sessionID": resp.fetchone()[0]
            }

    return {
        "sessionID": None
    }


@app.route('/signup', methods=['GET', 'POST'])
def signup():
    return {
        "working": True
    }


@app.route('/logout', methods=['GET', 'POST'])
def logout():
    return {
        "working": True
    }


@app.route('/profile', methods=['GET', 'POST'])
def profile():
    return {
        "working": True
    }


@app.route('/profile/save', methods=['GET', 'POST'])
def profileSave():
    return {
        "working": True
    }


@app.route('/profile/transactions', methods=['GET', 'POST'])
def profileTransactions():
    return [
        {
            "transactionID": "Random",
            "farmerID": "Random",
            "dateOfTransaction": "Random",
            "quantity": 10.1,
            "quality": 10.5,
            "societyID": 1020,
            "rate": 10.30
        },
        {
            "transactionID": "Random",
            "farmerID": "Random",
            "dateOfTransaction": "Random",
            "quantity": 10.1,
            "quality": 10.5,
            "societyID": 1020,
            "rate": 10.30
        },
        {
            "transactionID": "Random",
            "farmerID": "Random",
            "dateOfTransaction": "Random",
            "quantity": 10.1,
            "quality": 10.5,
            "societyID": 1020,
            "rate": 10.30
        },
    ]


@app.route("/profile/transaction")
def oneTransactionView():
    return {
        "transactionID": "Random",
            "farmerID": "Random",
            "dateOfTransaction": "Random",
            "quantity": 10.1,
            "quality": 10.5,
            "societyID": 1020,
            "rate": 10.30
    }


@app.route('/admin/login', methods=['GET', 'POST'])
def adminLogin():
    return {
        "working": True
    }


@app.route('/admin/signup', methods=['GET', 'POST'])
def adminSignup():
    return {
        "working": True
    }


@app.route('/admin/logout', methods=['GET', 'POST'])
def adminLogout():
    return {
        "working": True
    }


@app.route('/admin/viewAll', methods=['GET', 'POST'])
def adminViewAll():
    return {
        "working": True
    }


@app.route('/admin/viewOne', methods=['GET', 'POST'])
def adminViewOne():
    return {
        "working": True
    }


@app.route('/admin/viewOne/save', methods=['GET', 'POST'])
def adminViewOneSave():
    return {
        "working": True
    }


@app.route('/admin/viewOne/delete', methods=['GET', 'POST'])
def adminViewOneDelete():
    return {
        "working": True
    }


if __name__ == "__main__":
    app.run(host="localhost", port=8080, debug=True)