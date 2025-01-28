import { createContext, useContext } from "react";

// interface
import ContextActions from "../interfaces/ContextActions";

const MyContext = createContext<ContextActions | null>(null);

export const MyContextProvider = MyContext.Provider;
export const useMyContext = () => {
  const context = useContext(MyContext);
  if (!context) {
    throw new Error("Context not available");
  }
  return context;
};
