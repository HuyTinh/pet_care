import { toast } from "react-toastify";
import { SideBar } from "./components/side-bar";
import { CiSearch } from "react-icons/ci";

export const ReceptionistScreen = () => {
  const useToast = (message: string) => {
    toast.success(message, {
      position: "top-center",
    });
  };
  return (
    <div className="h-full">
      <div className="bg-blue-700/75 p-4 h-full flex">
        <SideBar />
        <div className="bg-white rounded-xl h-full flex-1 flex flex-col overflow-hidden p-5">
          <div className="flex gap-x-5">
            <label className="input input-bordered flex items-center gap-2 flex-1">
              <CiSearch />
              <input
                type="text"
                className="grow"
                placeholder="Search here...."
              />
            </label>
            <button className="btn">Create</button>
          </div>
          <div className="flex-1 py-5">
            <div className="overflow-x-auto !h-[38rem] border-solid border-2 rounded-xl">
              <table className="table table-xs table-pin-rows table-pin-cols">
                <thead>
                  <tr className="text-lg">
                    <th></th>
                    <td>Name</td>
                    <td>Job</td>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {Object.keys([...Array(30)]).map((e) => (
                    <tr key={e} className="h-24 *:text-lg">
                      <th>{e}</th>
                      <td>Cy Ganderton</td>
                      <td>Quality Control Specialist</td>
                      <td className="space-x-2">
                        <button className="btn">Edit</button>
                        <button
                          className="btn btn-success"
                          onClick={() =>
                            useToast("Approved appoinment successfull!")
                          }
                        >
                          Approved
                        </button>
                        <button
                          className="btn btn-error"
                          onClick={() =>
                            useToast("Cancel appoinment successfull!")
                          }
                        >
                          Cancel
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
