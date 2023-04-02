export interface Props {
  items: string[];
}

export const MainList: React.FC<Props> = (props) => {
  return (
    <ul>
      {props.items.map((item: string) => (
        <li>{item}</li>
      ))}
    </ul>
  );
};
