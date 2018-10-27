// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  url: 'http://localhost:8080',
  ampUrl: 'http://35.185.215.170:8090',
  firebase: {
    apiKey: "AIzaSyDyHfd479Iikb69iUgEiYSr-aD6KGyKIU0",
    authDomain: "xpay-test.firebaseapp.com",
    databaseURL: "https://xpay-test.firebaseio.com",
    projectId: "xpay-test",
    storageBucket: "xpay-test.appspot.com",
    messagingSenderId: "818053903785"
  },
  imgur: {
    clientId: '1cbd16bcd862570',
    clientSecret: '7b9fc704d7ca0a8c82fc2cfdca7cd3f06a51ac5e'
  }
};
