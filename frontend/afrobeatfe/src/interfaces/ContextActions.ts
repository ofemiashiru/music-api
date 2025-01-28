import { ArtistInput } from "../types/Artist";
import { GenreInput } from "../types/Genre";

interface ContextActions {
  doArtistAdd: (artistData: ArtistInput, file: File | null) => void;
  doArtistDelete: (id: string | undefined) => void;
  doGenreAdd: (genreData: GenreInput) => void;
  doGenreDelete: (id: number) => void;
}

export default ContextActions;
