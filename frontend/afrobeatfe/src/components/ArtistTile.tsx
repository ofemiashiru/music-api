// Types
import { Artist } from "../types/Artist";
import { Genre } from "../types/Genre";

// Styling
import "./ArtistTile.css";

// Context
import { useMyContext } from "../context/MyContext";

const ArtistTile = ({ artist }: { artist: Artist }) => {
  const { doArtistDelete } = useMyContext();
  const handleDelete = (id: string | undefined) => {
    doArtistDelete(id);
  };

  return (
    <div className="artist-tile">
      <h3>{artist.artistName}</h3>
      <img src={artist.artistImageUrl} />
      <p>{artist.genres.map((g: Genre) => g.genreName).join(", ")}</p>
      <button onClick={() => handleDelete(artist.id)}>
        Delete "{artist.artistName}"
      </button>
    </div>
  );
};

export default ArtistTile;
