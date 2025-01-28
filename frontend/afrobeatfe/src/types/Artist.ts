import { Genre, GenreArtistInput } from "./Genre";

export type Artist = {
  id?: string;
  artistName: string;
  artistImageUrl: string;
  genres: Genre[];
};

export type ArtistInput = {
  artistName: string;
  artistImageUrl: string;
  genres: GenreArtistInput[];
};
