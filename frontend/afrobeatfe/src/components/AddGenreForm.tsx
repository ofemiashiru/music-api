import { ChangeEvent, useState } from "react";

// Types
import { Genre, GenreInput } from "../types/Genre";

// Context
import { useMyContext } from "../context/MyContext";

const AddGenreForm = ({ currentGenres }: { currentGenres: Genre[] }) => {
  const [newGenre, setNewGenre] = useState<string>("");

  const { doGenreAdd, doGenreDelete } = useMyContext();

  const dataToSend: GenreInput = {
    genreName: newGenre,
  };

  const handleInputs = (e: ChangeEvent<HTMLInputElement>) => {
    const genreEntered = e.target.value;
    setNewGenre(genreEntered);
  };

  const handleAddGenre = (e: React.MouseEvent) => {
    e.preventDefault();

    doGenreAdd(dataToSend);

    setNewGenre("");
  };

  const handleDeleteGenre = (genreId: number) => {
    doGenreDelete(genreId);
  };
  return (
    <>
      <h1>Add Genre</h1>
      <form>
        <div>
          <label htmlFor="GenreName">Genre Name: </label>
          <input
            id="GenreName"
            type="text"
            name="genreName"
            value={newGenre}
            onChange={handleInputs}
          />
          <input type="submit" onClick={handleAddGenre} value="Add Genre" />
        </div>
      </form>
      <div>
        <p>Current Genres:</p>
        {currentGenres?.map((genre) => (
          <div key={genre.id}>
            <button onClick={() => handleDeleteGenre(genre.id)}>Delete</button>{" "}
            {genre.genreName}
          </div>
        ))}
      </div>
    </>
  );
};

export default AddGenreForm;
