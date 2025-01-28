import { gql } from "@apollo/client";

export const ADD_ARTIST = gql`
  mutation addArtist($artistData: ArtistInput) {
    addArtist(artistData: $artistData) {
      id
      artistName
      artistImageUrl
      genres {
        id
        genreName
      }
    }
  }
`;

export const DELETE_ARTIST = gql`
  mutation deleteArtist($id: ID!) {
    deleteArtist(id: $id)
  }
`;

export const ADD_GENRE = gql`
  mutation addGenre($genreData: GenreInput) {
    addGenre(genreData: $genreData) {
      genreName
      id
    }
  }
`;

export const DELETE_GENRE = gql`
  mutation deleteGenre($id: ID!) {
    deleteGenre(id: $id)
  }
`;
