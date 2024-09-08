import { useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'

export const AuthScreen = () => {
  const navigate = useNavigate()
  const onSubmit = () => {
    toast.success('Success Notification !', {
      position: 'top-center'
    })
    setTimeout(() => {
      navigate('client')
    }, 500)
  }

  return (
    <div className="bg-blue-700/75 h-full">
      <div className="flex justify-end items-center h-full p-10">
        <div className="bg-zinc-200 w-[92rem] h-full rounded-tl-3xl rounded-bl-3xl bg-[url('../assets/images/banner.png')] bg-no-repeat bg-contain bg-[center_top_4rem]" />
        <div className="bg-zinc-200 w-full h-full py-10 pr-24 flex justify-end rounded-tr-3xl rounded-br-3xl">
          <div className="backdrop-blur-xl bg-zinc-700/10 w-full rounded-3xl">
            <div className="p-4 pt-14">
              <form action="" className="m-6 space-y-8 flex flex-col">
                <h1 className="font-bold text-xl text-justify">
                  <span className="text-4xl bg-blue-700/75 px-2  rounded-xl text-white">Login</span>{' '}
                  to
                  <br /> Start taking{' '}
                  <span className="text-3xl bg-blue-700/75 px-2  rounded-xl text-white">
                    Care
                  </span>{' '}
                  pet
                </h1>
                <div className="space-y-6">
                  <label className="input input-bordered flex items-center gap-2 !rounded-xl">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 16 16"
                      fill="currentColor"
                      className="h-4 w-4 opacity-70"
                    >
                      <path d="M2.5 3A1.5 1.5 0 0 0 1 4.5v.793c.026.009.051.02.076.032L7.674 8.51c.206.1.446.1.652 0l6.598-3.185A.755.755 0 0 1 15 5.293V4.5A1.5 1.5 0 0 0 13.5 3h-11Z" />
                      <path d="M15 6.954 8.978 9.86a2.25 2.25 0 0 1-1.956 0L1 6.954V11.5A1.5 1.5 0 0 0 2.5 13h11a1.5 1.5 0 0 0 1.5-1.5V6.954Z" />
                    </svg>
                    <input type="text" className="grow text-lg" placeholder="examp@pcare.com" />
                  </label>
                  <label className="input input-bordered flex items-center gap-2 !rounded-xl">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 16 16"
                      fill="currentColor"
                      className="h-4 w-4 opacity-70"
                    >
                      <path
                        fillRule="evenodd"
                        d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z"
                        clipRule="evenodd"
                      />
                    </svg>
                    <input type="password" className="grow" placeholder="********" />
                  </label>
                </div>
                <button type="button" className="btn btn-neutral text-lg w-full" onClick={onSubmit}>
                  Login
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
