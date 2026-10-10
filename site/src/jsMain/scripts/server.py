from flask import Flask, render_template, jsonify, request
from flask_cors import CORS
import smtplib
from email.message import EmailMessage
from dotenv import load_dotenv
from datetime import datetime
import os

app = Flask(__name__)
CORS(app, resources={r"/api/*": {"origins": "http://localhost:8080", 
                                 "allow_headers": ["Content-Type"],
                                 "methods": ['POST', 'OPTIONS']}})

load_dotenv()

msg = EmailMessage()
msg['to'] = "ianmwendwa91e@gmail.com"
msg['from'] = "williamsian845@gmail.com"
msg['subject'] = "Thiccsters' Date Confirmation"
def send_email(date):
    password = os.getenv("SMTP_AUTH_PASS")
    msg.set_content(f"U + Freddy = Outside! When?:\n\n {date}!")
    try:
        print("Initiating Mwendwa email notification...")
        with smtplib.SMTP("smtp.gmail.com", 587) as server:
            server.starttls()
            server.login("williamsian845@gmail.com", password)
            server.send_message(msg)
        print("Email sent successfully!")

    except Exception as e:    
        print(f"Error sending email. Reason: {e}")
    

@app.route("/api/parker-data/", methods=['POST', 'OPTIONS'])
def get_selected_date_time():
    if request.method == 'OPTIONS':
        return jsonify({'status': 'Preflight ok'}), 200
    data = request.get_json()

    if not data:
        return jsonify({"status": "Did not receive any JSON payload. Check data src."}), 400

    selected_date_time = data.get('selectedDateTime')

    if not selected_date_time:
        return jsonify({"status": "Date time was not provided. Check data src."})

    print(f"Selected date time: {selected_date_time}")
    # If she rejects my proposal, send 'Another day' as the date
    if selected_date_time == "Nope":
        response = {
            "status": "Guess I'm summoning my boys",
            "data": "Doesn't change the fact that I lowkey miss you.",
            "raw_output": data
        }
        send_email("Another day. Summon Uncle Waffles and Rob.")
        return jsonify(response), 200

    send_email(selected_date_time)
    response = {
        "status": "Paparazzi! Prepare your lenses",
        "data": f"See you on: {selected_date_time}",
        "raw_output": data
    }
    return jsonify(response), 200

def parse_response():
    print(get_selected_date_time())
if __name__ == '__main__':
    app.run()
