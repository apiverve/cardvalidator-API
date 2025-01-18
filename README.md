Card Validator API
============

Card Validator is a simple tool for validating if a card number is valid or not. It checks the card number format and the Luhn algorithm to see if the card number is valid.

![Build Status](https://img.shields.io/badge/build-passing-green)
![Code Climate](https://img.shields.io/badge/maintainability-B-purple)
![Prod Ready](https://img.shields.io/badge/production-ready-blue)

This is a Javascript Wrapper for the [Card Validator API](https://apiverve.com/marketplace/api/cardvalidator)

---

## Installation
	npm install @apiverve/cardvalidator --save

---

## Configuration

Before using the cardvalidator API client, you have to setup your account and obtain your API Key.  
You can get it by signing up at [https://apiverve.com](https://apiverve.com)

---

## Usage

The Card Validator API documentation is found here: [https://docs.apiverve.com/api/cardvalidator](https://docs.apiverve.com/api/cardvalidator).  
You can find parameters, example responses, and status codes documented here.

### Setup

```
var cardvalidatorAPI = require('@apiverve/cardvalidator');
var api = new cardvalidatorAPI({
    api_key: [YOUR_API_KEY],
    secure: true //(Optional, defaults to true)
});
```

---


### Perform Request
Using the API client, you can perform requests to the API.

###### Define Query

```
var query = {
  number: "4900264223817524"
};
```

###### Simple Request (using Callback)

```
api.execute(query, function (error, data) {
    if (error) {
        return console.error(error);
    } else {
        console.log(data);
    }
});
```

###### Example Response

```
{
  "status": "ok",
  "error": null,
  "data": {
    "card": {
      "niceType": "Visa",
      "type": "visa",
      "patterns": [
        4
      ],
      "gaps": [
        4,
        8,
        12
      ],
      "lengths": [
        16,
        18,
        19
      ],
      "code": {
        "name": "CVV",
        "size": 3
      },
      "matchStrength": 1
    },
    "cardNumber": "4900264223817524",
    "isValid": true
  },
  "code": 200
}
```

---

## Customer Support

Need any assistance? [Get in touch with Customer Support](https://apiverve.com/contact).

---

## Updates
Stay up to date by following [@apiverveHQ](https://twitter.com/apiverveHQ) on Twitter.

---

## Legal

All usage of the APIVerve website, API, and services is subject to the [APIVerve Terms of Service](https://apiverve.com/terms) and all legal documents and agreements.

---

## License
Licensed under the The MIT License (MIT)

Copyright (&copy;) 2024 APIVerve, and Evlar LLC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.