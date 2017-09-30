(() => {

  const expect = require('chai').expect;
  const supertest = require('supertest');
  const server = supertest.agent('http://localhost:8080');

  describe('Book Service Tests', () => {

    let createdId = '';

    it('Should: Create a new Book', (done) => {
      server
        .post('/books')
        .send({ 'author': 'Hunter S. Thompson', 'title': 'Fear and Loathing in Las Vegas', 'genre': 'Fiction' })
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          expect(res.status).to.equal(201);
          expect(res.body.genre).to.equal('Fiction');
          createdId = res.body.id;
          done();
        });
    });

    it('Should Not: Create a new Book @fail', (done) => {
      server
        .post('/books')
        .send({ 'title': 'Fear and Loathing in Las Vegas', 'genre': 'Fiction' })
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          expect(res.status).to.equal(500);
          done();
        });
    });

    it('Should: Get all Books', (done) => {
      server
        .get('/books')
        .send()
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          expect(res.status).to.equal(200);
          expect(res.body.length).to.be.above(0);
          done();
        });
    });

    it('Should: Get a Book', (done) => {
      server
        .get('/books/' + createdId)
        .send()
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          expect(res.status).to.equal(200);
          expect(res.body.id).to.equal(createdId);
          done();
        });
    });

    it('Should Not: Get a Book @fail', (done) => {
      server
        .get('/books/999999')
        .send()
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          expect(res.status).to.equal(404);
          done();
        });
    });

    it('Should: Update a Book', (done) => {
      server
        .put('/books/' + createdId)
        .send({ 'author': 'Hunter Thompson', 'title': 'Fear and Loathing in Las Vegas', 'genre': 'Comedy' })
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          expect(res.status).to.equal(200);
          expect(res.body.genre).to.equal('Comedy');
          done();
        });
    });

    it('Should Not: Update a Book @fail', (done) => {
      server
        .put('/books/999999')
        .send({ 'title': 'Fear and Loathing in Las Vegas', 'genre': 'Comedy' })
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          expect(res.status).to.equal(500);
          done();
        });
    });

    it('Should: Delete a Book', (done) => {
      server
        .delete('/books/' + createdId)
        .send()
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          expect(res.status).to.equal(200);
          done();
        });
    });


    //
  //   it('Should Not: Create a new greeting @fail', (done) => {
  //
  //     server
  //       .put('/greetings?isCircuitBreakerTripped=false')
  //       .send({ 'content': ' ', 'email': 'James@gmail.com', 'type': 'HANDSHAKE' })
  //       .end((err, res) => {
  //         if (err) {
  //           return done(err);
  //         }
  //         expect(res.body.messages[1].message).to.contain(messages[0]);
  //         expect(res.body.messages[0].message).to.contain(messages[1]);
  //         expect(res.status).to.equal(400);
  //         done();
  //       });
  //   });
  //
  //   it('Should Not: Create a new greeting @fail', (done) => {
  //     server
  //       .put('/greetings?isCircuitBreakerTripped=true')
  //       .send({ 'content': ' ', 'email': 'James@gmail.com', 'type': 'HANDSHAKE' })
  //       .end((err, res) => {
  //         if (err) {
  //           return done(err);
  //         }
  //         expect(res.body.messages[1].message).to.contain(messages[0]);
  //         expect(res.body.messages[0].message).to.contain(messages[1]);
  //         expect(res.status).to.equal(400);
  //         done();
  //       });
  //   });
  //
  // });
  //
  // /***
  //  **
  //  **  Get Existing Greetings:
  //  **
  //  ***/
  //
  // describe('Get All Greetings', () => {
  //
  //   it('Should: Get list of greetings @fast', (done) => {
  //     server
  //       .get('/greetings')
  //       .set('Accept', 'application/json')
  //       .end((err, res) => {
  //
  //         if (err) {
  //           return done(err);
  //         }
  //         listOfIds = methods.getAllIds(res.body);
  //         expect(res.status).to.equal(200);
  //         done();
  //       });
  //   });
  //
  // });
  //
  // /**
  //  *
  //  **  Get an existing Greeting:
  //  *
  //  **/
  //
  // describe('Get an existing greeting', () => {
  //
  //   it('Should: Get an existing greeting @fast', (done) => {
  //     const greetingId = listOfIds[Math.floor(Math.random() * listOfIds.length)];
  //
  //     server
  //       .get('/greetings/' + greetingId)
  //       .set('Accept', 'application/json')
  //       .end((err, res) => {
  //         if (err) {
  //           return done(err);
  //         }
  //         expect(res.status).to.equal(200);
  //         done();
  //       });
  //   });
  //
  //   it('Should Not: Get a non existing greeting @fail', (done) => {
  //     let errorObj;
  //
  //     server
  //       .get('/greetings/892')
  //       .set('Accept', 'application/json')
  //       .end((err, res) => {
  //         if (err) {
  //           return done(err);
  //         }
  //         errorObj = JSON.parse(res.error.text);
  //         expect(res.status).to.equal(404);
  //         expect(errorObj.message).to.equal('Greeting : getById [892] NOT FOUND.');
  //         done();
  //       });
  //   });
  // });
  //
  // /**
  //  *
  //  **  Update an existing Greeting:
  //  *
  //  **/
  //
  // describe('Update an existing greeting', () => {
  //   const greetingId = listOfIds[Math.floor(Math.random() * listOfIds.length)];
  //
  //   it('Should: Update an existing greeting', (done) => {
  //     server
  //       .put('/greetings')
  //       .set('Accept', 'application/json')
  //       .send({ 'content': 'Updated User', 'email': 'Updated@gmail.com', 'id': greetingId, 'type': 'HANDSHAKE' })
  //       .end((err, res) => {
  //         if (err) {
  //           return done(err);
  //         }
  //         expect(res.status).to.equal(201);
  //         done();
  //       });
  //   });
  //
  //   it('Should Not: Update an existing greeting @fail', (done) => {
  //     let errorObj;
  //
  //     server
  //       .put('/greetings')
  //       .set('Accept', 'application/json')
  //       .send({ 'content': 'Updated User', 'email': 'Updated@gmail.com', 'id': greetingId, 'type': 'HANDSLAP' })
  //       .end((err, res) => {
  //         if (err) {
  //           return done(err);
  //         }
  //         errorObj = JSON.parse(res.error.text);
  //         expect(res.status).to.equal(400);
  //         expect(errorObj.messages[0].message).to.equal('Invalid greeting type. Valid values are HANDSHAKE, SMILE, VERBAL.');
  //         done();
  //       });
  //   });
  //
  // });
  //
  // /**
  //  *
  //  **  Delete an existing Greeting:
  //  *
  //  **/
  //
  // describe('Delete an existing greeting', () => {
  //
  //   it('Should: Delete an existing greeting', (done) => {
  //     const greetingId = listOfIds[Math.floor(Math.random() * listOfIds.length)];
  //
  //     server
  //       .delete('/greetings/' + greetingId)
  //       .set('Accept', 'application/json')
  //       .end((err, res) => {
  //         if (err) {
  //           return done(err);
  //         }
  //         expect(res.status).to.equal(200);
  //         done();
  //       });
  //   });
  //
  //   it('Should Not: Delete non existing greeting @fail', (done) => {
  //     server
  //       .delete('/greetings/898')
  //       .set('Accept', 'application/json')
  //       .end((err, res) => {
  //         if (err) {
  //           return done(err);
  //         }
  //         expect(res.status).to.equal(404);
  //         done();
  //       });
  //   });

  });

})();