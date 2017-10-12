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

    it('Should Not: Delete a Book @fail', (done) => {
      server
        .delete('/books/999999')
        .send()
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          expect(res.status).to.equal(404);
          done();
        });
    });

  });
})();