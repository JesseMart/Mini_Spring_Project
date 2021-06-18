const URL = 'http://localhost:8080/movie';

// To access the return values of these functions:

// i.e    getAllMovies().then((res) => console.log(res));
// OR
// await Promise.resolve(getAllMovies())

const getGenre = async (genre) => {
  let movies = null;
  let error = null;

  await axios
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
const getAllMovies = async () => {
  let movies = null;
  let error = null;

  await axios
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
const getMovieByID = async (movieID) => {
  let movie = null;
  let error = null;

  await axios
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
const createMovie = async (movieData) => {
  // movieData is an object
  // {
  //     title: "string",
  //     genre: "string",
  //     year: number
  // }

  let createdMovie = null;
  let error = null;

  await axios
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
const updateMovie = async (movieData) => {
  // MOVIEDATA for this request should have an ID field

  const { id } = movieData;

  let updatedMovie = null;
  let error = null;

  await axios
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
const updateMovieDetails = async (fields, movieID) => {
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

  await axios
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
const deleteMovie = async (movieID) => {
  let error = null;

  await axios
    .delete(`${URL}/delete/${movieID}`)
    .then((res) => {
      console.log(res); // no content
    })
    .catch((err) => {
      console.log(res);
    });

  return { error };
};

// DOM
const createEntry = (movieObj) => {
  const tableRow = document.createElement('tr');
  const checkboxTD = document.createElement('td');
  const checkboxContainer = document.createElement('span');
  const checkboxInput = document.createElement('input');

  checkboxInput.className = 'checkbox-input';
  checkboxInput.type = 'checkbox';
  checkboxInput.name = 'option[]';
  checkboxInput.value = '1';

  checkboxContainer.appendChild(checkboxInput);
  checkboxTD.appendChild(checkboxContainer);

  const titleTD = document.createElement('td');
  titleTD.className = `title-${movieObj.id}`;
  titleTD.innerHTML = `${movieObj.title}`;

  const genreTD = document.createElement('td');
  genreTD.className = `genre-${movieObj.id}`;
  genreTD.innerHTML = `${movieObj.genre}`;

  const yearTD = document.createElement('td');
  yearTD.className = `year-${movieObj.id}`;
  yearTD.innerHTML = `${movieObj.yearRelease}`;

  const functionalityTD = document.createElement('td');
  const editLink = document.createElement('button');
  const deleteLink = document.createElement('button');

  functionalityTD.appendChild(editLink);
  functionalityTD.appendChild(deleteLink);
  editLink.innerHTML = ' EDIT ';
  editLink.className = 'edit';
  deleteLink.innerHTML = ' DELETE ';
  deleteLink.className = 'delete';

  editLink.addEventListener('click', () => {
    const titleInput = document.querySelector('#edit-movie-title');
    const genreInput = document.querySelector('#edit-movie-genre');
    const yearInput = document.querySelector('#edit-movie-year');

    titleInput.name = movieObj.id;
    titleInput.value = movieObj.title;
    genreInput.value = movieObj.genre;
    yearInput.value = movieObj.yearRelease;

    document.querySelector('.edit-modal').classList.remove('hide');
  });

  tableRow.appendChild(checkboxTD);
  tableRow.appendChild(titleTD);
  tableRow.appendChild(genreTD);
  tableRow.appendChild(yearTD);
  tableRow.appendChild(functionalityTD);

  document.querySelector('.table-body').appendChild(tableRow);
};

const toggleModal = (targetElement) => {
  if (Array.from(targetElement.classList).indexOf('hide') === 1) {
    targetElement.classList.remove('hide');
  } else {
    targetElement.classList.add('hide');
  }
};

const addMovie = async () => {
  const title = document.querySelector('#add-movie-title');
  const genre = document.querySelector('#add-movie-genre');
  const year = document.querySelector('#add-movie-year');

  const movieData = {
    title: title.value,
    genre: genre.value,
    yearRelease: parseInt(year.value, 10),
  };

  await createMovie(movieData);
  toggleModal(document.querySelector('.add-modal'));

  title.value = null;
  genre.value = null;
  year.value = null;
};

window.addEventListener('load', async () => {
  //
  const movieList = await Promise.resolve(getAllMovies());

  for (let m of movieList.movies) {
    createEntry(m);
  }
});

const addMovieForm = document.querySelector('.add-modal');
addMovieForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  await addMovie();

  document.querySelector('.table-body').innerHTML = null;
  const movieList = await Promise.resolve(getAllMovies());

  for (let m of movieList.movies) {
    createEntry(m);
  }
});

const editMovieForm = document.querySelector('.edit-modal');
editMovieForm.addEventListener('submit', async (e) => {
  e.preventDefault();

  const titleInput = document.querySelector('#edit-movie-title');
  const genreInput = document.querySelector('#edit-movie-genre');
  const yearInput = document.querySelector('#edit-movie-year');

  const movieID = parseInt(titleInput.name, 10);

  const movieData = {
    id: movieID,
    title: titleInput.value,
    genre: genreInput.value,
    yearRelease: yearInput.value,
  };

  await updateMovie(movieData);

  document.querySelector('.table-body').innerHTML = null;
  const movieList = await Promise.resolve(getAllMovies());

  for (let m of movieList.movies) {
    createEntry(m);
  }

  toggleModal(document.querySelector('.edit-modal'));
});

const addBtn = document.querySelector('.add-btn');
addBtn.addEventListener('click', () => {
  toggleModal(document.querySelector('.add-modal'));
});
