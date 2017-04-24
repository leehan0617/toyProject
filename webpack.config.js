module.exports = {
  context: __dirname + '/webapp/js',
  entry: {main:'./webpacktest/index.js',jqeury:'./bower_components/jquery/index.js'},
  output: {
    path: __dirname + '/webapp/js/dist',
    filename: '[name].js'
  },
  module: {
	    loaders: [
	      { test: /\.(css|scss)$/, use: ['style-loader', 'css-loader','sass-loader'] }
	      , {
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
  }
};