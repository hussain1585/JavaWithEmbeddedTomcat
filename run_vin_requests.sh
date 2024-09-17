#!/bin/bash

# Path to the CSV file
CSV_FILE="vinNumbers.csv"

# Endpoint URL
BASE_URL="https://localhost:8080/decode/vinNumber="

# Skip the first line (header) and loop through each row of the CSV
tail -n +2 "$CSV_FILE" | while IFS=, read -r vinNumber; do
  # Send GET request for each VIN number
  response=$(curl -s -o /dev/null -w "%{http_code}" "${BASE_URL}${vinNumber}")

  # Print the response code and VIN number for tracking
  echo "VIN: $vinNumber - Response Code: $response"

done
