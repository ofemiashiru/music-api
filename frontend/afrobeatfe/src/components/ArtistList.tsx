// Types
import { Artist } from "../types/Artist";
import { ApolloError } from "@apollo/client";

// Component
import ArtistTile from "./ArtistTile";

// Style
import "./ArtistList.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faRotate } from "@fortawesome/free-solid-svg-icons";

const ArtistList = ({
  artists,
  isLoading,
  hasError,
}: {
  artists: Artist[];
  isLoading: boolean;
  hasError: undefined | ApolloError;
}) => {
  return (
    <>
      {hasError && <p>Error</p>}
      <p>
        {isLoading ? (
          <FontAwesomeIcon icon={faRotate} spin size="xl" />
        ) : (
          "Loaded"
        )}
      </p>
      <div className="artist-list">
        {artists?.length ? (
          artists.map((artist) => (
            <ArtistTile key={artist.id} artist={artist} />
          ))
        ) : (
          <p>No artists present</p>
        )}
      </div>
    </>
  );
};

export default ArtistList;
