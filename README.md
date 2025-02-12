# eCommerce report
This project is a backend application designed to calculate profit margins for orders in an e-commerce 
web application. It processes orders from a CSV file, calculates revenue and profit based on product 
details and payment methods, and generates reports using multi-threading for efficiency.

## Overview
The goal of this project is to calculate profit margins for orders in an e-commerce web application. 
The application reads order data from a CSV file, processes the data to calculate revenue and profit, 
and generates the following reports:

- Total revenue of the eComm
- Total profit of the eComm
- Profit per shirt size

The project uses the Strategy Pattern to implement payment processing and leverages multi-threading to 
generate reports efficiently.

## Features
CSV Input: Reads orders from a CSV file with columns: full_name, shirt_size, with_design, with_hoodie, payment.

### Pricing:

- Base shirt price: 14 BAM
- Design: +2 BAM
- Hoodie: +3 BAM
- Customer price: 40 BAM (fixed)

### Payment Fees:

- Wallet: 0%
- Bankcard: 5%
- Visa: 2%
- Mastercard: 3%
- Other: 10%

### Reports:

- Total revenue
- Total profit
- Profit per shirt size

Design Pattern: Strategy pattern for payment processing.
Multi-threading: Used for report generation.

