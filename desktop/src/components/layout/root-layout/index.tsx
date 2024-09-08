import { Outlet } from "react-router-dom";

export const RootLayout = () => {
  return (
    <div className="h-screen bg-black overflow-hidden">
      <Outlet />
    </div>
  );
};
