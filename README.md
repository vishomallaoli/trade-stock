# Stock Price Prediction Model :chart_with_upwards_trend:

Welcome to the Stock Price Prediction Model project! As the US stock market navigates through a Bear market, this project aims to leverage machine learning insights to predict stock prices, enabling users to make informed decisions about when to buy and sell stocks for maximum profit. This tool is designed to help manage retirement funds efficiently, embodying Dr. Chen's dream of achieving early retirement through smart investments.

## Overview :telescope:

This project introduces a command-line program, `TradeStock`, which predicts optimal purchasing (𝐴) and selling (𝐵) times from a series of stock prices to maximize profit. Inspired by the volatility witnessed during the GameStop stock phenomena, this model emphasizes safe investment strategies by disallowing short selling. The aim is to provide a reliable tool for investors to navigate the stock market more effectively.

![Stock Prediction Model](/[https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.investopedia.com%2Fnews%2Fwhy-amazons-stock-poised-rise-14%2F&psig=AOvVaw3aQTLS6ejReTIWvTYQ0O9_&ust=1712889113646000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCNi76qqPuYUDFQAAAAAdAAAAABAE])

## Features :sparkles:

- **Predictive Analysis**: Utilizes advanced algorithms to predict stock price movements.
- **Maximize Profits**: Identifies the best buy and sell points to ensure maximum profitability.
- **User-Friendly**: Simple command-line interface for easy use.
- **Data-Driven**: Processes time series data of stock prices for accurate predictions.

## Usage :computer:

To use the `TradeStock` program, provide the name of a binary file containing chronological stock prices and select the algorithm type:

```bash
java -jar TradeStock.jar <file_name> <algorithm_type_number>
```

Example command:

```bash
java -jar TradeStock.jar mystock0.bin 2
```

## Supported Algorithms :gear:

- `0`: Brute Force (Theta(n^2))
- `1`: Divide and Conquer (Theta(n log n))
- `2`: Linear Time (Theta(n)) Divide and Conquer (Extra Credit)
- `3`: Linear Time (Theta(n)) Decrease and Conquer (Extra Credit)

## Contributing :handshake:

Contributions to improve the Stock Price Prediction Model are welcome! Whether it's feature enhancements, bug fixes, or algorithm optimizations, your input is valued.

## Credits :star:

This project was developed by [Your Name or GitHub Username], inspired by Dr. Chen's innovative vision for applying machine learning to financial planning.

## License :page_facing_up:

This project is licensed under the MIT License - see the LICENSE.md file for details.
