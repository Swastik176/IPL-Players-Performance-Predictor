import SearchBar from './SearchBar';

function Heading({ title, placeholder, entityType, setSelection, role }) {
    return (
        <div>
            <div className="grid grid-cols-1 md:grid-cols-3 w-full px-4 py-4 gap-4">
                {/* Left Placeholder (desktop only) */}
                <div className="hidden md:block"/>

                {/* Centered Country */}
                <div className="text-center">
                    <h1 className="text-3xl font-semibold underline m-6 text-left lg:text-center">{title}</h1>
                </div>

                {/* Right aligned SearchBar */}
                <div className="flex justify-center items-center md:justify-end">
                    <SearchBar placeholder={placeholder} entityType={entityType} className='w-full mx-w-sm' setSelection={setSelection} role={role}/>
                </div>
            </div>
        </div>
    );
}

export default Heading;



