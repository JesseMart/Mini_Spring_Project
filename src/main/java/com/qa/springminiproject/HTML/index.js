const URL = 'http://localhost:8080/movie';

const getGenre = (genre) => {
  let movies = null;
  let error = null;

  axios
    .get(`${URL}/getGenre/${genre}`)
    .then((res) => {
      movies = res.data;
    })
    .catch((err) => {
      error = err;
    });

  return { movies, error };
};

// GET ALL MOVIES
const getAllMovies = () => {
  let movies = null;
  let error = null;

  axios
    .get(`${URL}/getAllMovies`)
    .then((res) => {
      movies = res.data;
    })
    .catch((err) => {
      error = err;
    });

  // we can use error to maybe render and error message in case of an error
  // if error is null, then it's a successfull request
  return { movies, error };
};

// GET MOVIE BY ID
const getMovieByID = (movieID) => {
  let movie = null;
  let error = null;

  axios
    .get(`${URL}/getOne/${movieID}`)
    .then((res) => {
      movie = res.data;
    })
    .catch((err) => {
      error = err;
    });

  return { movie, error };
};

// CREATE A MOVIE
const createMovie = (movieData) => {
  // movieData is an object
  // {
  //     title: "string",
  //     genre: "string",
  //     year: number
  // }

  let createdMovie = null;
  let error = null;

  axios
    .post(`${URL}/createMovie`, movieData)
    .then((res) => {
      createdMovie = res.data;
    })
    .catch((err) => {
      error = err;
    });

  return { movie: { ...createdMovie }, error };
};

// UPDATE MOVIE - PUT
const updateMovie = (movieData) => {
  // MOVIEDATA for this request should have an ID field

  const { id } = movieData;

  let updatedMovie = null;
  let error = null;

  axios
    .put(`${URL}/update/${id}`, movieData)
    .then((res) => {
      updatedMovie = res.data;
    })
    .catch((err) => {
      error = err;
    });

  return { movie: { ...updatedMovie }, error };
};

// UPDATE MOVIE - PATCH
// fields will be an object with key-value pairs with key being the field to update

// i.e in case we only want to update the TITLE
// {
//     title: "new title"
// }
const updateMovieDetails = (fields, movieID) => {
  const fieldsToArray = Array.from(fields);
  let queryParam = '';

  for (let i = 0; i < fieldsToArray.length; i++) {
    if (i === fieldsToArray.length - 1) {
      queryParam += `${fieldsToArray[i]}=${fields[fieldsToArray[i]]}`;
    } else {
      queryParam += `${fieldsToArray[i]}=${fields[fieldsToArray[i]]}&`;
    }
  }

  let error = err;

  axios
    .patch(`${URL}/update/${movieID}?${queryParam}`)
    .then((res) => {
      console.log(res); // this returns null in API
    })
    .catch((err) => {
      error = err;
    });

  return { movie: {}, error };
};

// DELETE a movie
const deleteMovie = (movieID) => {
  let error = null;

  axios
    .delete(`${URL}/delete/${movieID}`)
    .then((res) => {
      console.log(res); // no content
    })
    .catch((err) => {
      console.log(res);
    });

  return { error };
};
