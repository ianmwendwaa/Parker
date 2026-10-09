import re

# 2026-10-09T05:34

raw_date = "2026-10-09T05:34"
# Isolate the date from the time
clean_date = re.split(r'T', raw_date)

date_str, time_str = clean_date

# Isolate each date param
date_split = re.split(r'-', date_str)
year, month, date = date_split

month_name = ""
match month:
    case "01":
        month_name = "January"
    case "02":
        month_name = "February"
    case "03":
        month_name = "March"
    case "04":
        month_name = "April"
    case "05":
        month_name = "May"
    case "06":
        month_name = "June"
    case "07":
        month_name = "July"
    case "08":
        month_name = "August"
    case "09":
        month_name = "September"
    case "10":
        month_name = "October" 
    case "11":
        month_name = "November"
    case "12":
        month_name = "December" 

print(f"Neat date: {date} on the month of {month_name} {year}")                       

