function fn() {
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);

  if (!env) {
    env = 'dev';
  }

  var port = karate.properties['server.port'];
  var config = {
    env: env,
    baseUrl: 'http://localhost:' + port
  }

  if (env == 'dev') {
    // customize
    // e.g. config.foo = 'bar';
  } else if (env == 'e2e') {
    config.baseUrl = 'http://customer-service:8080';
  }

  return config;
} 