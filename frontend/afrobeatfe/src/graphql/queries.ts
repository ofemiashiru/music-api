import { gql } from "@apollo/client";

export const GET_ARTISTS = gql`
  query AllArtists {
    getArtists {
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

export const GET_GENRES = gql`
  query AllGenres {
    getGenres {
      id
      genreName
    }
  }
`;
