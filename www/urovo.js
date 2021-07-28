var exec = require('cordova/exec')
module.exports = {
  doScan : function(code, success, error) {
    exec(success, error, 'UrovoPDAPlugin', 'doScan', [code]);
  },
  
  onBarcodeScanned : function(success, error) {
    exec(success, error, 'UrovoPDAPlugin', 'onBarcodeScanned');
  }

}

