

export class Equipment {
  createdBy: any;
  constructor(
    public equipmentId: number,
    public equipmentName: string,
    public description: string,
    public brand: string,
    public quantity: number,
    public purchaseDate: Date,
  ) {

  }
}