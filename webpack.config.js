const ExtractTextPlugin = require("extract-text-webpack-plugin");
const webpack = require('webpack');
const CleanWebpackPlugin = require("clean-webpack-plugin");

const extractSass = new ExtractTextPlugin({
    filename: "[name].css"
});

module.exports = {
  context: __dirname + '/webapp/js',
  entry: {project:'./project',issue:'./issue',common:'./common',bootstrap:'./bootstrap.js',checkbox:'./checkbox.js',font:'./font.js'},
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
		  },
		  {    //웹포트 확장자(eot,woff,svg,ttf) 는 기본 mime type 에 등록 되어 있지 않기 때문에 설정을 추가 해줘야한다.
			  test: /\.(ico|png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
		      loader: 'url-loader',
		      options: {
		        name: '[name].[ext]',//[name]또는 [hash] 로 파일 명 지정 ,[ext]는 현재 확장자 그대로 하겠다는 뜻
		        limit: 10000,//limit 보다 작은 파일은 base64(data-uri)로 인코딩 해서 인라인화 한다., limit보다 큰 파일은 file-loader 가 자동으로 처리 (폰트 파일을 output.publicpath 에 복사하고, 해당 @import 문이 이 복사된 파일을 가르키도록 대체)
		      }
		  }
	    ]
  },
  node: {
	   fs: "empty"
	},
  plugins: [
//            new webpack.optimize.UglifyJsPlugin({
//            	compress: {
//            		warnings: false
//            		 drop_console: true
//            	}
//            }),
			extractSass,
			new CleanWebpackPlugin([__dirname+'/webapp/js/dist'])
          ]
};