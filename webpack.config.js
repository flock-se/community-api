const path = require('path')
const HtmlWebPackPlugin = require("html-webpack-plugin");

const htmlPlugin = new HtmlWebPackPlugin({
  template: path.resolve(__dirname, 'src/main/react/index.html'),
  filename: "./index.html"
});

module.exports = {

  entry: path.resolve(__dirname, 'src/main/react'),

  output: {
    path: path.resolve(__dirname, 'target/dist')
  },

  module: {
    rules: [
      {
        test: /\.js|jsx$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader"
        }
      }
    ]
  },

  resolve: {
    alias: {
      "eco-feature-users": path.resolve(__dirname, 'target/react/eco-feature-users-jar'),
    }
  },

  plugins: [htmlPlugin]

};