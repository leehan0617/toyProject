const ExtractTextPlugin = require("extract-text-webpack-plugin");
const webpack = require('webpack');

const extractSass = new ExtractTextPlugin({
    filename: "[name].css"
});

module.exports = {
  context: __dirname + '/webapp/js',
  entry: {main:'./project'},
  output: {
    path: __dirname + '/webapp/js/dist',
    filename: '[name].js'
  },
  module: {
	  rules: [
	      { test: /\.(css|scss)$/, 
    	    loader: extractSass.extract({
                use: [{
                    loader: "css-loader"
                }, {
                    loader: "sass-loader"
                }],
                fallback: "style-loader"
            })
	      }, 
	      {
		        test: /\.js$/,
		        loader: 'babel-loader',
		        exclude: /(node_modules|bower_components)/,
		        options: {
		            presets: [
		              'es2015'
		            ]
		          }
		  }
	    ]
  },
  plugins: [
            new webpack.optimize.UglifyJsPlugin({
            	compress: {
            		warnings: false
            		, drop_console: true
            	}
            }),
			extractSass
          ]
};