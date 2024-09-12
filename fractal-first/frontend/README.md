# YanchwareGo

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 17.3.5.

## Development server

Run `ng serve` or `npm run start` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

Run `npm run server-start` to start the openapi mocker server, it's served on the 1234 port.

## Documentation

Run `npm run generate-documentation` to generate documentation with compodoc tool and start a local server to consult it

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Open API Mocker

To install the `open-api-mocker` package globally, run the following command:
```npm i -g open-api-mocker```

## Open API Start Mock Server
To start the mock server run the `server-start` script:
```npm run server start```

Or you can choose to launch direct the follow command in the root of this project:
```open-api-mocker -s server/sr.frontend.challenge.yml -p 1234 -w```

`-p 1234` set the local port, feel free to choose whatever you want

