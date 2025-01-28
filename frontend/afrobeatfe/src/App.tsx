import { useEffect, useState } from "react";
import { useQuery, useMutation } from "@apollo/client";

// GraphQL Query and Mutation Schemas
import { GET_ARTISTS, GET_GENRES } from "./graphql/queries";
import {
  ADD_ARTIST,
  DELETE_ARTIST,
  ADD_GENRE,
  DELETE_GENRE,
} from "./graphql/mutations";

// Types
import { Artist, ArtistInput } from "./types/Artist";
import { Genre, GenreInput } from "./types/Genre";

// Components
import ArtistList from "./components/ArtistList";
import AddArtistForm from "./components/AddArtitstForm";
import AddGenreForm from "./components/AddGenreForm";

// Context
import { MyContextProvider } from "./context/MyContext";

function App() {
  const [artists, setArtists] = useState<Artist[]>([]);
  const [genres, setGenres] = useState<Genre[]>([]);

  // GraphQL Queries and Mutations
  const {
    error: getArtistsError,
    loading: loadingArtists,
    data: artistsData,
    refetch: refetchArtists,
  } = useQuery(GET_ARTISTS);

  const {
    error: getGenresError,
    loading: loadingGenres,
    data: genresData,
    refetch: refetchGenres,
  } = useQuery(GET_GENRES);

  const [addTheArtist, { error: addArtistError }] = useMutation(ADD_ARTIST);
  const [deleteTheArtist, { error: deleteArtistError }] =
    useMutation(DELETE_ARTIST);

  const [addTheGenre, { error: addGenreError }] = useMutation(ADD_GENRE);
  const [deleteTheGenre, { error: deleteGenreError }] =
    useMutation(DELETE_GENRE);

  // Use Effect
  useEffect(() => {
    setArtists(artistsData?.getArtists);
    setGenres(genresData?.getGenres);
  }, [artistsData, genresData]);

  const handleArtistAdd = async (
    artistData: ArtistInput,
    file: File | null
  ) => {
    // const formData = new FormData();
    // if (file) {
    //   formData.append("file", file);
    // }

    // let imageUploadData;

    // const response = await fetch("http://localhost:8080/api/upload", {
    //   method: "POST",
    //   body: formData,
    // });

    // if (response.ok) {
    //   imageUploadData = await response.json();
    //   artistData.artistImageUrl = imageUploadData.url;
    // }

    artistData.artistImageUrl = await uploadFile(file);

    const { data } = await addTheArtist({
      variables: {
        artistData: artistData,
      },
    });

    if (addArtistError) {
      console.log(addArtistError);
      throw new Error(addArtistError.message);
    }

    if (data) {
      refetchArtists();
    }
  };

  const handleArtistDelete = async (artistId: string | undefined) => {
    const { data: deleteData } = await deleteTheArtist({
      variables: { artistId },
    });

    if (deleteArtistError) {
      console.log(deleteArtistError);
      throw new Error(deleteArtistError.message);
    }

    if (deleteData.deleteArtist) {
      refetchArtists();
    }
  };

  const handleGenreAdd = async (genreData: GenreInput) => {
    const { data } = await addTheGenre({
      variables: {
        genreData: genreData,
      },
    });

    if (addGenreError) {
      console.log(addArtistError);
      throw new Error(addArtistError?.message);
    }

    if (data) {
      refetchGenres();
    }
  };

  const handleGenreDelete = async (genreId: number) => {
    const { data: deleteData } = await deleteTheGenre({
      variables: {
        id: genreId,
      },
    });

    if (deleteGenreError) {
      console.log(deleteGenreError);
      throw new Error(deleteGenreError.message);
    }

    if (deleteData.deleteGenre) {
      refetchGenres();
    }
  };

  const uploadFile = async (file: File | null) => {
    const formData = new FormData();
    if (file) {
      formData.append("file", file);
    }

    let imageUploadData;

    const response = await fetch("http://localhost:8080/api/upload", {
      method: "POST",
      body: formData,
    });

    if (response.ok) {
      imageUploadData = await response.json();
      return imageUploadData.url;
    } else {
      return null;
    }
  };

  return (
    <MyContextProvider
      value={{
        doArtistDelete: handleArtistDelete,
        doArtistAdd: handleArtistAdd,
        doGenreAdd: handleGenreAdd,
        doGenreDelete: handleGenreDelete,
      }}
    >
      <>
        <h1>Afrobeats Lib Admin</h1>
        <ArtistList
          artists={artists}
          isLoading={loadingArtists}
          hasError={getArtistsError}
        />
        <hr />
        <AddArtistForm genres={genres} />
        <hr />
        <AddGenreForm currentGenres={genres} />
      </>
    </MyContextProvider>
  );
}

export default App;
