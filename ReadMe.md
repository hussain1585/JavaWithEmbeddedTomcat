chmod +x run_vin_requests.sh

./run_vin_requests.sh

response=$(curl -s "${BASE_URL}${vinNumber}")
echo "VIN: $vinNumber - Response: $response"
