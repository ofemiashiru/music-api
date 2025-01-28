import React, { ChangeEvent, useState } from "react";

// Types
import { ArtistInput } from "../types/Artist";
import { Genre, GenreArtistInput } from "../types/Genre";

// Context
import { useMyContext } from "../context/MyContext";

const AddArtistForm = ({ genres }: { genres: Genre[] }) => {
  const { doArtistAdd } = useMyContext();

  const [artistName, setArtistName] = useState<string>("");
  const [artistImageUrl, setArtistImageUrl] = useState<string>("");
  const [newArtistGenres, setNewArtistGenres] = useState<GenreArtistInput[]>(
    []
  );

  const [imageFile, setImageFile] = useState<File | null>(null);

  const dataToSend: ArtistInput = {
    artistName: artistName,
    artistImageUrl: artistImageUrl,
    genres: newArtistGenres,
  };

  const handleInputs = (e: ChangeEvent<HTMLInputElement>) => {
    const properties: { [key: string]: () => void } = {
      artistName: () => setArtistName(e.target.value),
      artistImageUrl: () => setArtistImageUrl(e.target.value),
    };

    const files = e.target.files;

    if (files && files.length > 0) {
      console.log(files[0]);
      const selectedFile: File = files[0];
      setImageFile(selectedFile);
    }

    properties[e.target.name]();
  };

  const handleGenresData = (e: ChangeEvent<HTMLInputElement>) => {
    const clickedGenreId = parseInt(e.target.value);
    let newGenres = [...newArtistGenres];
    const genreExists = newGenres.find((genre) => genre.id == clickedGenreId);

    if (!genreExists) {
      const selectedGenre = genres.find((genre) => genre.id == clickedGenreId);

      if (selectedGenre) {
        setNewArtistGenres([...newGenres, { id: selectedGenre.id }]);
      } else {
        console.log("Genre does not exist");
      }
    } else {
      newGenres = newGenres.filter((genre) => genre.id != clickedGenreId);
      setNewArtistGenres([...newGenres]);
    }
  };

  const handleAddArtist = async (e: React.MouseEvent) => {
    e.preventDefault();

    doArtistAdd(dataToSend, imageFile);

    setArtistName("");
    setArtistImageUrl("");
    setNewArtistGenres([]);

    // Clear checkboxes
    const allGenreCheckboxes: HTMLCollectionOf<Element> =
      document.getElementsByClassName("genre-checkbox");

    for (const checkbox of allGenreCheckboxes) {
      checkbox.checked = false;
    }
  };

  return (
    <div>
      <h1>Add Artist</h1>
      <form>
        <div>
          <label htmlFor="ArtistName">Artist Name: </label>
          <input
            id="ArtistName"
            type="text"
            name="artistName"
            onChange={handleInputs}
            value={artistName}
          />
        </div>
        <div>
          <label htmlFor="ArtistImage">Image: </label>
          <input
            id="ArtistImage"
            type="file"
            name="artistImageUrl"
            onChange={handleInputs}
            value={artistImageUrl}
          />
        </div>
        <div>
          <p>Genres:</p>
          {genres?.map((genre) => (
            <div key={genre.id}>
              <input
                type="checkbox"
                name={genre.genreName}
                id={`${genre.genreName}_id`}
                value={genre.id}
                onChange={handleGenresData}
                className="genre-checkbox"
              />
              <label htmlFor={`${genre.genreName}_id`}>{genre.genreName}</label>
            </div>
          ))}
        </div>
        <div>
          <input type="submit" value="Add Artist" onClick={handleAddArtist} />
        </div>
      </form>
    </div>
  );
};

export default AddArtistForm;
